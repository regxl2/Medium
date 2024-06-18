import { Context, Hono } from 'hono';
import { userRouter } from './routes/user';
import { blogRouter } from './routes/blog';
import { PrismaClient } from '@prisma/client/edge';
import { withAccelerate } from '@prisma/extension-accelerate';
import { cors } from 'hono/cors';

const app = new Hono<{
	Bindings: {
		DATABASE_URL: string,
		JWT_SECRET: string
	}
}>();

export function getClient(c: Context) {
	return new PrismaClient({
		datasourceUrl: c.env.DATABASE_URL
	}).$extends(withAccelerate());
}

export function unAuthorizedMessage(c: Context){
	c.status(401);
	return c.json({ error: 'Unauthorized.' });
}

export function incorrectInputMessage(c: Context){
	c.status(411);
	return c.json({ error: 'Inputs are incorrect.' });
}

app.use('api/v1/*', cors());
app.route('api/v1/user', userRouter);
app.route('api/v1/blog', blogRouter);

export default app;
