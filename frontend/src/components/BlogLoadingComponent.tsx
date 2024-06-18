export const BlogLoadingComponent = () => {
	return (
		<div className={"grid grid-cols-12 gap-8 m-16 flex-1 animate-pulse"}>
			<div className={"flex flex-col col-span-8"}>
				<div className={'mb-4 h-[3.75rem] bg-slate-300'}/>
				<div className={'mb-4 bg-slate-300 h-[1.75rem] w-[15rem]'}/>
				<div className={"bg-slate-300 flex-grow"}/>
			</div>
			<div className={"col-span-4"}>
				<div className={"mb-4 bg-slate-300 h-[1.75rem] w-[5rem]"}/>
				<div className={"flex flex-row items-center"}>
					<span className={`flex items-center justify-center rounded-full w-10 h-10 bg-slate-300 mr-2`} />
					<div className={"bg-slate-300 h-[1.75rem] w-[15rem]"}/>
				</div>
			</div>
		</div>
	)
}