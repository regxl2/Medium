interface LoadingButtonProps {
	text: string;
	color?: string
	textColor?: string
}

export const LoadingButton = ({text, color = 'bg-black', textColor = 'text-white'}:LoadingButtonProps)=>{
	return (
		<button className={`mt-4 p-3 rounded-xl ${color} ${textColor} block w-full`}>
			{text}
		</button>
	)
}