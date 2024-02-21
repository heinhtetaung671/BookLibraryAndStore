/**
 * 
 */
document.addEventListener('DOMContentLoaded', () => {
	const searchForm = document.getElementById('searchForm')
	const activeSelect = document.getElementById('activeSelect')
	
	if(searchForm && activeSelect){
		activeSelect.addEventListener('change', () => searchForm.submit())
	}
})