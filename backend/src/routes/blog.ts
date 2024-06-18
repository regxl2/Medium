import { Hono } from 'hono';
import { verify } from 'hono/jwt';
import { getClient, incorrectInputMessage, unAuthorizedMessage } from '../index';
import {createBlogInput, updateBlogInput} from '@regxl/medium-common';


export const blogRouter = new Hono<{
	Bindings: {
		DATABASE_URL: string,
		JWT_SECRET: string
	},
	// This is also a way of set a value in middleware
	// Variables: Variables
}>();


// type Variables = {
// 	userId: string
// }

// Token verification middleware
blogRouter.use('*', async (c, next) => {
	const jwt = c.req.header('authorization');
	if (!jwt) {
		return unAuthorizedMessage(c);
	}
	const token = jwt.split(' ')[1];
	try{
		const payLoad = await verify(token, c.env.JWT_SECRET);
		if (!payLoad) {
			return unAuthorizedMessage(c);
		}
		c.set('jwtPayload', payLoad);
		await next();
	}
	catch(err){
		return unAuthorizedMessage(c);
	}
});

blogRouter.post('/', async (c) => {
	const userId = c.get('jwtPayload').id;
	const body = await c.req.json();
	const { success } = createBlogInput.safeParse(body);
	if(!success){
		c.status(411);
		return c.json({
			error: "Inputs are incorrect."
		})
	}
	const client = getClient(c);
	let response;
	try {
		const post = await client.post.create({
			data: {
				title: body.title,
				content: body.content,
				authorId: userId
			}
		});
		response = { id: post.id };
	} catch (err) {
		c.status(500);
		response = { err };
	}
	return c.json(response);
});

blogRouter.put('/', async (c) => {
	const userId = c.get('jwtPayload').id;
	const body = await c.req.json();
	const { success } = updateBlogInput.safeParse(body);
	if(!success){
		return incorrectInputMessage(c);
	}
	const client = getClient(c);
	let response;
	try {
		await client.post.update({
			where: {
				id: body.id,
				authorId: userId
			},
			data: {
				title: body.title,
				content: body.content
			}
		});
		response = { message: 'Post updated successfully' };
	} catch (err) {
		c.status(500);
		response = {err}
	}
	return c.json(response);
});

blogRouter.get('/bulk', async (c) => {
	const client = getClient(c);
	let response;
	try{
		const posts = await client.post.findMany({
			select: {
				id: true,
				title: true,
				content: true,
				published: true,
				createdAt: true,
				author: {
					select:{
						name: true
					}
				}
			}
		});
		response = {posts};
	}
	catch(err){
		c.status(500);
		response = {err};
	}
	return c.json(response);
});

blogRouter.get('/:id', async (c) => {
	const postId = c.req.param('id');
	const client = getClient(c);
	let response;
	try {
		const post = await client.post.findUnique({
			where:{
				id: postId
			},
			select: {
				id: true,
				title: true,
				content: true,
				published: true,
				createdAt: true,
				author: {
					select:{
						name: true
					}
				}
			}
		});
		response = {post};
	}
	catch(err){
		c.status(500);
		response = {err};
	}
	return c.json(response);
});

