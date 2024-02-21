/**
 * 
 */
document.addEventListener('DOMContentLoaded', () => {
	const promotionSelectBoxs = document.getElementsByClassName('promotionSelectBox')
	const promotionSelectLink = document.getElementById('promotionSelectLink')
	
	if(promotionSelectBoxs && promotionSelectLink){
		Array.from(promotionSelectBoxs).forEach(promotionSelectBox =>
			 promotionSelectBox.addEventListener('change', () => {
			promotionSelectLink.href = promotionSelectLink + '/' + promotionSelectBox.dataset.id + '/' + promotionSelectBox.value
			promotionSelectLink.click()
		}))
	}
	
})