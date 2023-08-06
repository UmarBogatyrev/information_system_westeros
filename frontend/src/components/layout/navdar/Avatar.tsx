'use client';

import { FC } from 'react'
import Image from 'next/image';

const Avatar = () => {
  return (
  	<Image
		className="rounded-full"
		height = "30"
		width = "30"
		alt = "Avatar"
		src = "/images/profile_icon.png"
	/>

	);
}

export default Avatar