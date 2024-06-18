export const ErrorComponent = ({ message }: { message: string }) => {
	return (
		<div className={'flex items-center justify-center flex-1 text-4xl text-center text-red-500'}>{message}</div>
	);
};