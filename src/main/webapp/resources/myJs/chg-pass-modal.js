/**
 * 
 */

 document.addEventListener('DOMContentLoaded', () => {
	 const chgPassModal = document.getElementById('chgPassModal')
	 const chgPassCloseBtn = document.getElementById('chgPassCloseBtn')
	 const chgPassBtn = document.getElementById('chgPassBtn')
	 
	 if(chgPassBtn && chgPassCloseBtn && chgPassModal){
		 const chgPassModalDislog = new bootstrap.Modal(chgPassModal)
		 
		 if(chgPassModal.dataset.chgpasserror == 'true'){
			 chgPassModalDislog.show()
		 }
		 
		 chgPassBtn.addEventListener('click', () => chgPassModalDislog.show())
		 
		 chgPassCloseBtn.addEventListener('click', () => chgPassModalDislog.hide())
	 }
 })