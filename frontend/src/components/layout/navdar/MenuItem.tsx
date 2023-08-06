'use client';

interface MenuItemProps {
	onClick: () => void;
	icons: any
	label: string;
}

const MenuItem: React.FC<MenuItemProps> = ({
	onClick,
	icons,
	label
}) => {
  return (
  	<div 
		onClick={ onClick }
		className='flex flex-row px-4 py-3 hover:bg-neutral-100 transition'>
			<div className='mr-[10px] text-2xl'>
					{ icons } 
				</div>
			<div className='font-semibold pt-[2px]'>
					{ label }
			</div>
	</div>
  )
}

export default MenuItem