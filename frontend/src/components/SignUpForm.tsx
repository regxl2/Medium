import { LabelInput } from './LabelInput.tsx';
import { Button } from './Button.tsx';
import { Link, useNavigate } from 'react-router-dom';
import { SignUpInput } from '@regxl/medium-common';
import axios, { AxiosError } from 'axios';
import { memo, useCallback, useState } from 'react';
import { UserState } from '../screenState/UserState.ts';
import { LoadingButton } from './LoadingButton.tsx';
import { BASE_URL } from '../config.ts';
import { jwtDecode } from 'jwt-decode';

export interface JWTPayload{
	id: string,
	name: string
}

export const SignUpForm = () => {
	const [state, setState] = useState<UserState>({ type: 'start' });

	const navigate = useNavigate();
	const [name, setName] = useState('');
	const [email, setEmail] = useState('');
	const [password, setPassword] = useState('');
	const onNameChange = useCallback((value: string): void => {
		setName(value.trim());
	}, []);
	const onEmailChange = useCallback((value: string) => {
		setEmail(value);
	},[]);
	const onPasswordChange = useCallback((value: string) => {
		setPassword(value);
	}, []);

	const submitRequest = useCallback(async () => {
		setState({ type: 'loading' });
		try {
			const signUpInput: SignUpInput = {name, email, password};
			const response = await axios.post(`${BASE_URL}/user/signup`, signUpInput);
			const data = response.data;
			localStorage.setItem('token', data.jwt);
			const payload: JWTPayload = jwtDecode(data.jwt);
			localStorage.setItem('mediumUser', payload.name);
			localStorage.setItem('mediumUserId', payload.id);
			navigate('/blogs');
		} catch (err) {
			if (err instanceof AxiosError && err.status!==500) {
				setState({ type: 'error', message: err.response?.data.error ||'Something went wrong'});
			}
			else{
				setState({ type: 'error', message: 'Something went wrong' });
			}
		}
	}, [name, email, password]);

	return (
		<div className={'flex flex-col flex-1 items-center justify-center h-screen bg-white'}>
			<div className={'inline-block w-2/5'}>
				<FormHeading/>
				<FormInput onNameChange={onNameChange} onEmailChange={onEmailChange} onPasswordChange={onPasswordChange}/>
				<FormButton state={state} submitRequest={submitRequest}/>
			</div>
		</div>
	);
};

const FormHeading = memo(() => {
	return (
		<div className={'flex flex-col items-center justify-center'}>
			<h1 className={'font-bold text-4xl my-1'}>{'Create an account'}</h1>
			<h2 className={'font-bold text-slate-400 text-xl my-2'}>{`'Already have an account? `}
				<Link className={'underline cursor-pointer'} to={'/signin'}>{'Login'}</Link></h2>
		</div>
	);
});


interface FormInputProps{
	onNameChange: (value: string)=> void,
	onEmailChange: (value: string)=> void,
	onPasswordChange: (value: string )=> void
}
const FormInput = memo(({onNameChange, onEmailChange, onPasswordChange}:FormInputProps)=>{
	return(
		<>
			<LabelInput label={'Name'} placeholder={'Enter your name'} onValueChange={onNameChange} />
			<LabelInput label={'Email'} placeholder={'abc@example.com'} onValueChange={onEmailChange} />
			<LabelInput label={'Password'} type={'password'} onValueChange={onPasswordChange} />
		</>
	);
});


interface FormButtonProps{
	state: UserState;
	submitRequest: ()=> void;
}
const FormButton = memo(({state, submitRequest}: FormButtonProps)=>{
	return(
		<>
			{state.type === 'loading' ? <LoadingButton text={'Loading...'} color={'bg-green-500'} /> :
				<Button text={'Sign up'} onClick={submitRequest} />}
			<div className={'my-1 text-red-500 text-center'}>{state.type === 'error' ? state.message : ' '}</div>
		</>
	);
});

