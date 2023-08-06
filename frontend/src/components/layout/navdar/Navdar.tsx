'use client';

import { FC } from 'react'
import Link from 'next/link'
import Logo from './Logo'
import styles from './Navdar.module.scss'
import UserMenu from './UserMenu'

const Navdar: FC = () => {
  return (
	<div className= { styles.container }>
		<div className={ styles.menu_nav }>
		<Logo/>
		<div className={ styles.menu_button }>
		<Link href=''>Главная</Link>
		<Link href=''>Аналитика</Link>
		<Link href=''>Налоги</Link>
		</div>
		<UserMenu/>
	</div>
	</div>
)

}

export default Navdar