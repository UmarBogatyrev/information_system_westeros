import { FC } from 'react'
import styles from './Header.module.scss'
import Navdar from '../navdar/Navdar'



const Header: FC = () => {
  return ( <header className={styles.header}>
	<Navdar/>
	</header>
  )
}

export default Header


