import { Quotes } from '../components/Quotes.tsx';
import { SignInForm } from '../components/SignInForm.tsx';
import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

export const SignIn = ()=>{
	const token = localStorage.getItem('token');
	const navigate = useNavigate();
	useEffect(()=>{
		if(token!=null){
			navigate('/blogs')
		}
	}, []);
	return(
		<div className={"flex flex-row items-center justify-center"}>
			<SignInForm/>
			<Quotes/>
		</div>
	)
}