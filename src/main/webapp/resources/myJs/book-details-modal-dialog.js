/**
 * 
 */
document.addEventListener('DOMContentLoaded', () => {
	const bookDetailsModalDialog = document.getElementById('bookDetailsModalDialog')
	const closeBtn = document.getElementById('closeBtn')
	
	if(bookDetailsModalDialog && closeBtn){
		const dialog = new bootstrap.Modal(bookDetailsModalDialog)
		dialog.show()
		
		closeBtn.addEventListener('click', () => dialog.hide())		
	}
})