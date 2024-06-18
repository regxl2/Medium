export interface Start{
	type: 'start'
}
export type Loading = {
	type: 'loading'
}
export interface Error {
	type: 'error'
	message: string;
}
export interface Post{
	id: string,
	title: string,
	content: string,
	authorName: string,
	createdAt: string
}