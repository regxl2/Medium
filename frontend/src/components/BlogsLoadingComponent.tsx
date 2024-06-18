export const BlogsLoadingComponent = () => {
	return (
		<div className={"flex flex-col items-center flex-1"}>
			<Skeleton/>
			<Skeleton/>
			<Skeleton/>
		</div>
	);
};

const Skeleton = ()=> {
	return (
			<div className={"inline-flex flex-col animate-pulse m-4 min-w-[56rem]"}>
				<div className={'flex flex-row items-center mb-2 '}>
					<span className={`flex items-center justify-center rounded-full w-10 h-10 bg-slate-300 text-slate-50 mr-2`} />
					<span className={'mr-2 font-semibold h-4 w-10 bg-slate-300'} />
					<span className={'mr-2 h-1 w-1 bg-slate-300 rounded-full'} />
					<span className={'mr-2 font-semibold h-4 w-20 bg-slate-300'} />
				</div>
				<div className={'mb-2'}>
					<div className={'mb-2 font-semibold h-8 bg-slate-300'} />
					<div className={'mb-2 font-semibold h-28 bg-slate-300'} />
				</div>
				<div className={'font-semibold h-4 w-20 bg-slate-300'} />
			</div>
	)
}