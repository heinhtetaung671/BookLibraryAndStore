/**
 * 
 */

 document.addEventListener('DOMContentLoaded', () => {
	 const addNewBtn = document.getElementById('addNewBtn')
	const modalDialog = document.getElementById('modalDialog')
	const closeBtn = document.getElementById('closeBtn')
	const editBtns = document.getElementsByClassName('editBtn')
	
	if(addNewBtn && modalDialog && closeBtn && editBtns){
		const modal = new bootstrap.Modal(modalDialog)
		
		Array.from(editBtns).forEach(editBtn => {
			
			editBtn.addEventListener('click', () =>{
			document.getElementById('id').value = editBtn.dataset.id
			document.getElementById('name').value = editBtn.dataset.name
			document.getElementById('active').value = editBtn.dataset.active
			modal.show()})
		})
			
		
		if(modalDialog.dataset.error == 'true'){ modal.show()}
		
		addNewBtn.addEventListener('click', () => {
			document.getElementById('id').value = 0
			document.getElementById('name').value = ''
			document.getElementById('active').value = 'true'
			modal.show()
		}) 
		
		closeBtn.addEventListener('click', () => modal.hide())
	}

 })