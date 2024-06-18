import { Avatar } from './Avatar.tsx';
import { Link } from 'react-router-dom';

interface BlogCardProps {
	id: string,
	authorName: string
	publishedDate: string,
	title: string,
	content: string,
}

export const BlogCard = ({id, authorName, publishedDate, title, content}: BlogCardProps)=>{
	return (
		<Link className={'m-10 border-b-2'} to={`/blog/${id}`}>
			<CardHeader authorName={authorName} publishedDate={publishedDate} />
			<CardBody title={title}
								content={content} />
			<CardFooter readTime={`${Math.ceil(content.length / 300)} min(s) read`} />
		</Link>
	)
}

interface CardHeaderProps {
	authorName: string,
	publishedDate: string
}

const CardHeader = ({ authorName, publishedDate }: CardHeaderProps) => {
	return (
		<div className={'flex flex-row items-center mb-2'}>
			<Avatar nameInitial={authorName[0].toUpperCase()} backgroundColor={"bg-slate-500"}/>
			<span className={'mr-2 font-semibold'}>{authorName}</span>
			<span className={'mr-2 h-1 w-1 bg-black rounded-full'}></span>
			<span className={'text-slate-500 '}>{publishedDate}</span>
		</div>
	);
};

interface CardBodyProps {
	title: string,
	content: string
}

const CardBody = ({ title, content }: CardBodyProps) => {
	return (
		<div className={'mb-2'}>
			<div className={'font-bold text-2xl mb-2'}>{title}</div>
			<div className={'text-xl text-slate-600'}>{content.length>300? content.slice(0, 300)+'...': content}</div>
		</div>
	)
}

const CardFooter = ({readTime}:{readTime: string})=>{
	return(
		<div className={"text-slate-500 mb-2"}>{readTime}</div>
	)
}