import { compare, hash } from 'bcryptjs';
import { User } from '@prisma/client';
import { Hono } from 'hono';
import { sign } from 'hono/jwt';
import { getClient, incorrectInputMessage } from '../index';
import {signUpInput, signInInput} from '@regxl/medium-common';

export const userRouter = new Hono<{
	Bindings: {
		DATABASE_URL: string,
		JWT_SECRET: string
	}
}>();

async function hashPassword(password: string) {
	return await hash(password, 10);
}

async function userExist(user: User | null, password: string) {
	return user != null && await compare(password, user.password);
}

// sign up route
userRouter.post('/signup', async (c) => {
	const client = getClient(c);
	const body = await c.req.json();
	const {success} = signUpInput.safeParse(body);
	if(!success){
		return incorrectInputMessage(c);
	}
	let msg;
	try {
		const checkUser = await client.user.findUnique({
			where: {
				email: body.email
			}
		});
		// checking user if it already exists.
		const userAlreadyExist = await userExist(checkUser, body.password);
		if (userAlreadyExist) {
			c.status(409);
			return c.json({
				error: 'User with this email already exists.'
			});
		}
		// creating the user
		const hash = await hashPassword(body.password);
		const user = await client.user.create({
			data: {
				name: body.name,
				email: body.email,
				password: hash
			}
		});
		// creating token
		const token = await sign({ id: user.id , name: user.name}, c.env.JWT_SECRET);
		msg = { jwt: token };
	} catch (err) {
		// catching error if something went wrong.
		c.status(500);
		msg = { err };
	}
	return c.json(msg);
});

// Sign in route
userRouter.post('/signin', async (c) => {
	const client = getClient(c);
	const body = await c.req.json();
	const { success } = signInInput.safeParse(body);
	if(!success){
		return incorrectInputMessage(c);
	}
	let msg;
	try {
		// finding the user with email.
		const checkUser = await client.user.findUnique({
			where: {
				email: body.email
			}
		});
		// validating the user
		const isValidUser = await userExist(checkUser, body.password);
		if (!isValidUser) {
			c.status(403);
			return c.json({
				error: 'User doesn\'t exist or incorrect password'
			});
		}
		// creating token
		const token = await sign({ id: checkUser!.id, name: checkUser!.name}, c.env.JWT_SECRET);
		msg = { jwt: token };
	} catch (err) {
		c.status(500);
		msg = { err };
	}
	return c.json(msg);
});