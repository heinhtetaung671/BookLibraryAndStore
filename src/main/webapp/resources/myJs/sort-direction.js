/**
 * 
 */
document.addEventListener('DOMContentLoaded', () => {
	const directionBtn = document.getElementById('directionBtn')
	const searchForm = document.getElementById('searchForm')
	const direction = document.getElementById('direction')
	
	if(searchForm && directionBtn && direction){

			console.log(direction.value)
		directionBtn.addEventListener('click', () => {
			if(direction.value == 'asc'){
				direction.value = 'desc'
				searchForm.submit()
			}
			
			if(direction.value == 'desc'){
				direction.value = 'asc'
				searchForm.submit()
			}
	
		})
	}
})