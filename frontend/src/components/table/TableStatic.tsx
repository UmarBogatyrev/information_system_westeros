import { FC } from 'react'
import styles from './Table.module.scss'

const TableStatic: FC = () => {
  return (
  	<div className={ styles.table_max }>
		<div className='flex flex-col text-center mt-[30px] font-semibold font-sans'>
			СТАТИСТИКА
		</div>
	</div>)
}
 
export default TableStatic

