import z from 'zod';

export const signUpInput = z.object({
	email: z.string().email(),
	password: z.string().min(8).regex(/^[a-zA-Z0-9@#$%^&*]+$/),
	name: z.string().min(1)
})
export const signInInput = z.object({
	email: z.string().email(),
	password: z.string().min(8).regex(/^[a-zA-Z0-9@#$%^&*]+$/)
})
export const createBlogInput = z.object({
	title: z.string(),
	content: z.string(),
	authorId: z.string()
})
export const updateBlogInput = z.object({
	title: z.string(),
	content: z.string()
})

export type SignUpInput = z.infer<typeof signUpInput>;
export type SignInInput = z.infer<typeof signInInput>;
export type CreateBlogInput = z.infer<typeof createBlogInput>;
export type updateBlogInput = z.infer<typeof updateBlogInput>;


