interface ButtonProps {
	text: string;
	onClick: () => void;
	color?: string
	textColor?: string
}

export const Button = ({text, onClick, color = 'bg-black', textColor = 'text-white'}:ButtonProps)=>{
	return (
		<button className={`mt-4 p-3 rounded-xl ${color} ${textColor} block w-full hover:bg-slate-500`} onClick={onClick}>
			{text}
		</button>
	)
}