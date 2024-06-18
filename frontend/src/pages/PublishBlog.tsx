import { AppBar } from '../components/AppBar.tsx';
import { useCallback, useEffect, useRef, useState } from 'react';
import JoditEditor from 'jodit-react';
import { UserState } from '../screenState/UserState.ts';
import axios, { AxiosError } from 'axios';
import { BASE_URL } from '../config.ts';
import { useNavigate } from 'react-router-dom';

export const PublishBlog = () => {
	const navigate = useNavigate();
	const token = localStorage.getItem('token');

	useEffect(()=>{
		if(token==null){
			navigate('/signin');
		}
	}, []);

	const userName = localStorage.getItem('mediumUser') || 'Anonymous';
	const [title, setTitle] = useState('');
	const onChangeTitle = useCallback((value: string) => {
		setTitle(value);
	}, []);
	const editor = useRef(null);
	const [content, setContent] = useState('');
	const [state, setState] = useState<UserState>({ type: 'start' });

	const onClickPublish = async () => {
		setState({ type: 'loading' });
		try {
			const id = localStorage.getItem('mediumUserId')||'';
			await axios.post(`${BASE_URL}/blog`, { title: title==''?'untitled':title, content, authorId: id },
				{ headers: { Authorization: `Bearer ${token}`} });
			navigate('/blogs');
		} catch (err) {
			if (err instanceof AxiosError && err.status !== 500) {
				setState({ type: 'error', message: err.response?.data.error || 'Something went wrong' });
			} else {
				setState({ type: 'error', message: 'Something went wrong' });
			}
		}
	};
	return (
		<div className={'h-screen flex flex-col'}>
			<AppBar nameInitial={userName[0].toUpperCase()} to={'/blogs'} />
			<div className={'flex flex-col items-center my-4'}>
				<div className={'max-w-4xl'}>
					<TitleInput label={'Title'} placeholder={'Title'} onValueChange={onChangeTitle} />
					<div className={'text-2xl font-bold mb-1 w-full'}>{'Content'}</div>
					<div className={'mb-4'}>
						<JoditEditor value={content} ref={editor} onChange={newContent => {
							setContent(newContent);
						}} />
					</div>
					{state.type === 'loading' ? <DisabledButton /> : <PublishButton onClick={onClickPublish} text={'Publish'} />}
					{state.type === 'error' ? <div className={'text-red-500 text-center'}>{state.message}</div> : null}
				</div>
			</div>
		</div>
	);
};

interface TitleInputProps {
	label: string;
	placeholder: string;
	onValueChange: (value: string) => void;
}

const TitleInput = ({ label, placeholder, onValueChange }: TitleInputProps) => {
	return (
		<div className={'my-4'}>
			<div className={'text-2xl font-bold mb-1 w-full'}>{label}</div>
			<input
				className={'px-2 py-2 border-4 rounded-xl  outline-none border-gray-300 block w-full focus:border-blue-300'}
				placeholder={placeholder} onChange={(e) => {
				onValueChange(e.target.value);
			}} />
		</div>
	);
};


const PublishButton = ({ text, onClick }: { text: string, onClick: () => void }) => {
	return (
		<div
			onClick={onClick}
			className={'inline-block mb-4 mr-4 bg-green-500 px-5 py-2 h-10 text-slate-50 border rounded-full hover:cursor-pointer hover:bg-green-600'}>{text}</div>
	);
};

const DisabledButton = () => {
	return (
		<div
			className={'inline-block mb-4 mr-4 bg-green-600 px-5 py-2 h-10 text-slate-50 border rounded-full'}>{'Publishing...'}</div>
	);
};