import { Error, Loading, Post } from './commonStateTypes.ts';

export type BlogState = Loading | Success | Error

interface Success{
	type: "success",
	post: Post
}
