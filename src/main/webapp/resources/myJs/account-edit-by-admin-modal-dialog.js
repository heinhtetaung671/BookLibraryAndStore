 document.addEventListener('DOMContentLoaded', () => {
	const modalDialog = document.getElementById('modalDialog')
	const editBtns = document.getElementsByClassName('editBtn')
	const closeBtn = document.getElementById('closeBtn')
	
	if( modalDialog && editBtns){
		
		const modal = new bootstrap.Modal(modalDialog)
		
		if(modalDialog.dataset.error == 'true'){
			modal.show()
		}
		
		Array.from(editBtns).forEach(editBtn => editBtn.addEventListener('click', () => {
			
			Array.from(document.getElementsByClassName('errorTag')).forEach(e => e.innerHTML = '')
			
			document.getElementById('id').value = editBtn.dataset.id
			document.getElementById('name').value = editBtn.dataset.name
			document.getElementById('email').value = editBtn.dataset.email
			document.getElementById('phone').value = editBtn.dataset.phone == undefined ? '' : editBtn.dataset.phone
			document.getElementById('authority').value = editBtn.dataset.authority
			document.getElementById('balance').value = editBtn.dataset.balance
			document.getElementById('point').value = editBtn.dataset.point
			document.getElementById('active').value = editBtn.dataset.active
			document.getElementById('locked').value = editBtn.dataset.locked
			document.getElementById('statusMessage').value = editBtn.dataset.statusmessage == undefined ? '' : editBtn.dataset.statusmessage
			
			modal.show()
		}))
				
		closeBtn.addEventListener('click', () => {
			document.getElementById('searchForm').submit()
			modal.hide()
		})
	}
	
	
})