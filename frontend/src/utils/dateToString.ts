export const dateToString = (date: Date)=>{
	const months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
	const newDate = new Date(date);
	const year = newDate.getFullYear();
	const month = months[newDate.getMonth()];
	const day = newDate.getDate();
	return `${day} ${month} ${year}`;
}