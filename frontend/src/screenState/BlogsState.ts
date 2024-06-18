import { Loading, Error, Post } from './commonStateTypes.ts';

export type BlogsState = Loading | Success | Error;

interface Success {
	type: 'success',
	posts: Array<Post>
}

