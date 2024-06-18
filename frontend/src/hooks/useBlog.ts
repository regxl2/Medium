import { useEffect, useState } from 'react';
import { BlogState } from '../screenState/BlogState.ts';
import axios, { AxiosError } from 'axios';
import { Post } from '../screenState/commonStateTypes.ts';
import { dateToString } from '../utils/dateToString.ts';
import { BASE_URL } from '../config.ts';
import { useNavigate } from 'react-router-dom';


export const useBlog = (id: string) => {
	const [state, setState] = useState<BlogState>({ type: 'loading' });
	const token = localStorage.getItem('token');
	const navigate = useNavigate();
	useEffect(() => {
		if(token==null){
			navigate('/signin');
		}
		const fetchPost = async () => {
			try {
				console.log(BASE_URL);
				const response = await axios.get(`${BASE_URL}/blog/${id}`, {
					headers: {
						Authorization: `Bearer ${token}`
					}
				});
				const responsePost = response.data.post;
				const post: Post = {
					id: responsePost.id,
					title: responsePost.title,
					content: responsePost.content,
					createdAt: dateToString(responsePost.createdAt),
					authorName: responsePost.author.name
				};
				setState({ type: 'success', post: post });
			} catch (err) {
				if (err instanceof AxiosError) {
					setState({ type: 'error', message: err.response?.data.error || 'Something went wrong' });
				} else {
					setState({ type: 'error', message: 'Something went wrong' });
				}
			}
		};
		fetchPost();
	}, []);
	return state;
};