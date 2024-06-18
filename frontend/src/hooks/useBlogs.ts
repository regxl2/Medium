import { useEffect, useState } from 'react';
import axios, { AxiosError } from 'axios';
import { BlogsState } from '../screenState/BlogsState.ts';
import { dateToString } from '../utils/dateToString.ts';
import { BASE_URL } from '../config.ts';
import { useNavigate } from 'react-router-dom';


interface GetPost{
	id: string;
	title: string;
	content: string;
	createdAt: Date;
	published: boolean;
	author: Author
}

interface Author{
	name: string;
}

export const useBlogs = ()=>{
	const [state, setState] = useState<BlogsState>({type: "loading"})
	const token = localStorage.getItem('token');
	const navigate = useNavigate();
	useEffect(() => {
		if(token==null){
			navigate('/signin');
		}
		const fetchPosts = async () => {
			try {
				const res = await axios.get(`${BASE_URL}/blog/bulk`, {
					headers: {
						Authorization: `Bearer ${token}`,
					},
				});
				const posts = res.data.posts.map((post: GetPost) => ({
					id: post.id,
					title: post.title,
					content: post.content,
					authorName: post.author.name,
					createdAt: dateToString(post.createdAt)
				}));
				setState({ type: 'success', posts: posts});
			} catch (err) {
				if (err instanceof AxiosError) {
					setState({ type: 'error', message: err.response?.data.error|| "Something went wrong" });
				} else {
					setState({ type: 'error', message: 'Something went wrong' });
				}
			}
		};
		fetchPosts();
	}, []);

	return state;
}
