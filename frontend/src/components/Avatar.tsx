import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

interface AvatarProps {
	nameInitial: string;
	backgroundColor: string;
}

export const Avatar = ({ nameInitial, backgroundColor }: AvatarProps) => {
	const [hidden, setHidden] = useState(true);
	const navigate = useNavigate();
	return (
		<div onClick={() => {setHidden(!hidden)}} className={"flex flex-col items-center relative hover:cursor-pointer"}>
			<div className={`flex items-center justify-center rounded-full w-10 h-10 ${backgroundColor} text-slate-50 mx-2`}>
				{nameInitial}
			</div>
			<div onClick={async ()=>{
				localStorage.removeItem('token');
				localStorage.removeItem('mediumUser');
				localStorage.removeItem('mediumUserId');
				navigate('/signin');
			}} className={`${hidden? 'hidden': 'block'} absolute mt-2 top-full bg-white border-4 rounded border-gray-300 px-4 py-2 hover:cursor-pointer`}>
				{"Logout"}
			</div>
		</div>
	);
};