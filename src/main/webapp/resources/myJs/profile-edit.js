/**
 * 
 */

 document.addEventListener('DOMContentLoaded', () => {
	 const editProfileBtn = document.getElementById('editProfileBtn')
	 const profileModal = document.getElementById('profileModal')
	 const closeBtn = document.getElementById('closeBtn')
	 const passwordModal = document.getElementById('passwordModal')
	 const saveBtn = document.getElementById('saveBtn')
	 const closePassBtn = document.getElementById('closePassBtn')

	 if(editProfileBtn && profileModal && passwordModal && saveBtn && closePassBtn){
		 const modal = new bootstrap.Modal(profileModal)
		 const passModal = new bootstrap.Modal(passwordModal)
		 
		 if(profileModal.dataset.error == 'true'){
			 modal.show()
		 }
		 
		 if(profileModal.dataset.passworderror == 'true'){
			 passModal.show()
		 }
		 
		 saveBtn.addEventListener('click', () => {
			 passModal.show()
			 
			})
		 
		 editProfileBtn.addEventListener('click', () => modal.show())
		 
		 closeBtn.addEventListener('click', () => modal.hide())

		 closePassBtn.addEventListener('click', () => {
			 modal.hide()
			 passModal.hide()
		 } )
	 }

 })