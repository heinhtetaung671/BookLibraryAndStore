/**
 * 
 */
document.addEventListener('DOMContentLoaded', () => {
	const searchForm = document.getElementById('searchForm')
	const sortBy = document.getElementById('sortBy')
	
	const sortBySelect = document.getElementById('sortBySelect')
	
	if(searchForm && sortBy && sortBySelect){
		sortBySelect.addEventListener('change', () => {
			sortBy.value = sortBySelect.value
			searchForm.submit()
		})
	}
})