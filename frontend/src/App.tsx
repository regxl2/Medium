import './App.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { SignUp } from './pages/SignUp.tsx';
import { SignIn } from './pages/SignIn.tsx';
import { Blog } from './pages/Blog.tsx';
import {Blogs} from './pages/Blogs.tsx';
import { PublishBlog } from './pages/PublishBlog.tsx';

function App() {
	return (
		<>
			<BrowserRouter>
				<Routes>
					<Route path={'/signup'} element={<SignUp />} />
					<Route path={'/signin'} element={<SignIn />} />
					<Route path={`/blog/:id`} element={<Blog/>} />
					<Route path={'/blogs'} element={<Blogs/>} />
					<Route path={'/publish'} element={<PublishBlog/>} />
				</Routes>
			</BrowserRouter>
		</>
	);
}

export default App;
