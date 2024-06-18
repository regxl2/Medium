import { Avatar } from './Avatar.tsx';
import { Link } from 'react-router-dom';

export const AppBar = ({ nameInitial, to, displayButton }: { nameInitial: string, to: string, displayButton: boolean }) => {
	return (
		<>
			<div className={'flex flex-row justify-between items-center w-full px-16 py-4'}>
				<Link className={'font-bold text-4xl cursor-pointer'} to={to}>{'Medium'}</Link>
				<div className={'flex flex-row justify-between items-center'}>
					{displayButton?<NewBlogButton to={'/publish'}/>:null}
					<Avatar nameInitial={nameInitial} backgroundColor={'bg-slate-500'} />
				</div>
			</div>
			<hr />
		</>
	);
};


const NewBlogButton = ({ to }: {to:string}) => {
	return (
		<Link to={to}>
			<div
				className={'mr-4 bg-green-500 px-5 py-2 h-10 text-slate-50 border rounded-full hover:cursor-pointer hover:bg-green-600'}>{'New'}</div>
		</Link>
	);
};

