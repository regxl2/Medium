import { Quotes } from '../components/Quotes.tsx';
import { SignUpForm } from '../components/SignUpForm.tsx';
import { useNavigate } from 'react-router-dom';
import { useEffect } from 'react';

export const SignUp = ()=>{
	const token = localStorage.getItem('token');
	const navigate = useNavigate();
	useEffect(()=>{
		if(token!=null){
			navigate('/blogs')
		}
	}, []);
	return(
		<div className={"flex flex-row items-center justify-center"}>
			<SignUpForm/>
			<Quotes/>
		</div>
	)
}