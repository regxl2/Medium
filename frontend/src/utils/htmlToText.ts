export const htmlToText = (htmlText: string):string =>{
	const p = document.createElement('p');
	p.innerHTML = htmlText;
	return p.innerText||"";
}