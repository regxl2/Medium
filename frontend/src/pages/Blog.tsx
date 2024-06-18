import { Avatar } from '../components/Avatar.tsx';
import { AppBar } from '../components/AppBar.tsx';
import { useBlog } from '../hooks/useBlog.ts';
import { Post } from '../screenState/commonStateTypes.ts';
import { useParams } from 'react-router-dom';
import { ErrorComponent } from '../components/ErrorComponent.tsx';
import { BlogLoadingComponent } from '../components/BlogLoadingComponent.tsx';

export const Blog = () => {
	const {id}= useParams();
	const state = useBlog(id||'');
	const userName = localStorage.getItem('mediumUser')||"Anonymous";
	let content;
	switch(state.type){
		case 'loading':  content =  <BlogLoadingComponent/>; break;
		case 'error':  content = <ErrorComponent message={state.message} />; break;
		case 'success':  content =  <SuccessComponent post = {state.post}/>; break;
	}
	return (
		<div className={"h-screen flex flex-col"}>
			<AppBar nameInitial={userName[0].toUpperCase()} to={'/blogs'} displayButton={false}/>
			{content}
		</div>
	)
};

interface SuccessComponentProps {
	post: Post
}
const SuccessComponent = ({ post}: SuccessComponentProps)=>{
	const {title, content, createdAt, authorName} = post;
	return(
			<div className={"grid grid-cols-12 gap-8 m-16 flex-1"}>
				<ArticleComponent title={title} createdAt={createdAt} content={content} />
				<AuthorComponent authorName={authorName} />
			</div>
	)
}



interface ArticleComponentProps {
	title: string,
	createdAt: string,
	content: string
}

export const ArticleComponent = ({ title, createdAt, content }: ArticleComponentProps) => {
	return (
		<div className={"col-span-8"}>
			<div className={'mb-4 font-bold text-6xl'}>{title}</div>
			<div className={'mb-2 text-slate-500 text-xl'}>{`Posted on ${createdAt}`}</div>
			<div className={"text-xl"}>
				<div dangerouslySetInnerHTML={{__html: content}}/>
			</div>
		</div>
	);
};


export const AuthorComponent = ({ authorName }: { authorName: string }) => {
	return (
		<div className={"col-span-4"}>
			<div className={"mb-4 text-xl"}>{"Author"}</div>
			<div className={"flex flex-row items-center"}>
				<Avatar nameInitial={authorName[0].toUpperCase()} backgroundColor={"bg-slate-500"} />
				<div className={"text-2xl font-bold"}>{authorName}</div>
			</div>
		</div>
	);
};