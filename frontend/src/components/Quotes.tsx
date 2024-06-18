import React from 'react';

export const Quotes = React.memo(()=>{
	return (
		<div className = {"hidden lg:flex flex-col flex-1 items-center justify-center h-screen bg-slate-200 "}>
			<div className={"mx-16"}>
				<h1 className={"mb-8 font-semibold text-4xl"}>{'\"To acquire knowledge, one must study; but to acquire wisdom, one must observe.\"'}</h1>
				<h2 className={"font-bold text-2xl mb-2"}>{"Marilyn vos Savant"}</h2>
				<h3 className={"font-semibold text-xl text-slate-500"}>{"American-writer"}</h3>
			</div>
		</div>
	)
})