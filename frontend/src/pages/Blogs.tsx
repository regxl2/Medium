import { BlogCard } from '../components/BlogCard.tsx';
import { AppBar } from '../components/AppBar.tsx';
import { useBlogs } from '../hooks/useBlogs.ts';
import { Post } from '../screenState/commonStateTypes.ts';
import { ErrorComponent } from '../components/ErrorComponent.tsx';
import { BlogsLoadingComponent } from '../components/BlogsLoadingComponent.tsx';
import { htmlToText } from '../utils/htmlToText.ts';

export const Blogs = () => {
	const state = useBlogs();
	const userName = localStorage.getItem('mediumUser')||"Anonymous";
	let content;
	switch (state.type) {
		case 'loading': content = <BlogsLoadingComponent />; break;
		case 'error': content = <ErrorComponent message={state.message} />; break;
		case 'success': content =<SuccessComponent posts={state.posts}/>; break;
	}
	return(
		<div className={'h-screen flex flex-col'}>
			<AppBar nameInitial={userName[0].toUpperCase()} to={'/blogs'} displayButton={true}/>
			{content}
		</div>
	)
};

const SuccessComponent = ({posts }: {posts: Array<Post> }) => {
	return (
		<>
			<div className={'flex flex-col items-center flex-1'}>
				<div className={'max-w-4xl'}>
					{
						posts.map((post) => {
							return (<BlogCard key={post.id} id={post.id} authorName={post.authorName} publishedDate={post.createdAt} title={post.title}
															 content={htmlToText(post.content)} />);
						})
					}
				</div>
			</div>
		</>
	);
};