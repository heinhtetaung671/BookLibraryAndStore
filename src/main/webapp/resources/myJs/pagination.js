/**
 * 
 */

 document.addEventListener('DOMContentLoaded', () => {
	 const searchForm = document.getElementById('searchForm')
	 const page = document.getElementById('page')
     const pageSize = document.getElementById('pageSize')
		 
	 const pagination = document.getElementById('pagination')
	 const pageSizeSelect = document.getElementById('pageSizeSelect')
	 
	 const buttons = pagination.getElementsByTagName('button')
	 if(searchForm && page && pagination){
		 Array.from(buttons).forEach(button => button.addEventListener('click',() => {
		 page.value = button.dataset.value
		 searchForm.submit()
	 }))
	 
	 }	 
	 
	 if(pageSize && pageSizeSelect && searchForm && page){
		 pageSizeSelect.addEventListener('change', () => {
			 pageSize.value = pageSizeSelect.value
			 page.value = 0
			 searchForm.submit()
		 })
	 }
	 
 })