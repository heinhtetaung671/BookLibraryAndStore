/**
 * 
 */
document.addEventListener('DOMContentLoaded', () => {
	const returnBtns = document.getElementsByClassName('returnBtn');
	const doReturnLink = document.getElementById('doReturnLink');
	const confirmBoxModal = document.getElementById('confirmBoxModal')
	const closeBtn = document.getElementById('closeBtn')
	const doReturnUrl = '/customer/return/book/'
	
	if(closeBtn && confirmBoxModal && doReturnLink && returnBtns){
		const modal = new bootstrap.Modal(confirmBoxModal)
		
		Array.from(returnBtns).forEach(returnBtn => {
			returnBtn.addEventListener('click', () => {
				doReturnLink.href = doReturnUrl + returnBtn.dataset.id
				modal.show()
			})
		})
		
		closeBtn.addEventListener('click', () => {
			doReturnLink.href = ''
			modal.hide()
		})
	}
})