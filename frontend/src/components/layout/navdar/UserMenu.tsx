'use client';

import { useCallback, useEffect, useRef, useState } from 'react'
import { AiOutlineMenu, AiOutlineMessage } from "react-icons/ai"
import { VscSettingsGear } from "react-icons/vsc";
import Avatar from './Avatar'
import MenuItem from './MenuItem'
import { ImExit} from "react-icons/im";
import { useOutsideClick } from './useOutsideClick'
import { useRouter } from 'next/navigation'

const UserMenu = () => {
  const [isOpen, setIsOpen] = useState(false);
  const router = useRouter();
  
  const ref = useOutsideClick(() => setIsOpen(false));

  const toggleOpen = useCallback(() => {
	  setIsOpen((value) => !value);
  }, []);

  return (
  	<div className='relative mr-[201px]'>
		<div className='flex flex-row items-center gap-3'>
			<div className='hidden md:block'>
					<Avatar/>
			</div>
			<div
				onClick={() => {}}
				className='
					hidden
					md:block
					text-sm
					font-semibold
					py-3
					px-0
					text-[#fff]
				'
			>
				Cersei Lannister
			</div>
			<div 
				ref={ref}
				onClick={toggleOpen}
				className='
					p-4
					md:py-1
					md:px-2
					border-neutral-200
					flex
					flex-row
					gap-3
					rounded-full
					cursor-pointer
					hover:shadow-md
					transition
					bg-[#fff]
					
				'>
				<AiOutlineMenu/>
			</div>
		</div>
		{isOpen && (
		<div
				className='
					absolute
					rounded-xl 
					shadow-md
					w-[40vw]
					md:w-5/6
					bg-white
					overflow-hidden
					right-8
					top-12
					text-sm
	
					
				'>
					<div className='flex flex-col cursor-pointer'>
						<>
							<MenuItem
								onClick={() => {}}
								icons={<AiOutlineMessage/>}
								label='Сообщение'
							/>
							<MenuItem
								onClick={() => {}}
								icons={<VscSettingsGear/>}
								label='Настройки'
							/>
							<MenuItem
								icons={<ImExit/>}
								label='Выход'				onClick={() => router.push('http://localhost:3000/login')}
							/>
						</>
					</div>	
			</div>
		)}
  	</div>
  )
}

export default UserMenu