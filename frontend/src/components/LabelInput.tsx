interface LabelInputProps {
	label: string;
	placeholder?: string;
	type?: string;
	onValueChange: (value: string) => void;
}

export const LabelInput = ({ label, placeholder, type, onValueChange }: LabelInputProps) => {
	return (
		<div className={'my-2'}>
			<h3 className={' font-bold mb-1 w-full'}>{label}</h3>
			<input
				className={'px-2 py-2 border-4 rounded-xl  outline-none border-gray-300 block w-full focus:border-blue-300'}
				placeholder={placeholder} type={type} onChange={(e) => {
				onValueChange(e.target.value);
			}} />
		</div>
	);
}
