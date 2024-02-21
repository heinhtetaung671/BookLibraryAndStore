/**
 * 
 */

 document.addEventListener('DOMContentLoaded', () => {
	 const profilePhotoForm = document.getElementById('profilePhotoForm')
	 const profilePhotoBtn = document.getElementById('profilePhotoBtn')
	 const profilePhotoInput = document.getElementById('profilePhotoInput')
	 
	 if(profilePhotoBtn && profilePhotoForm && profilePhotoInput){
		 profilePhotoBtn.addEventListener('click', () => profilePhotoInput.click())
		 profilePhotoInput.addEventListener('change', () => profilePhotoForm.submit())
	 }
 })