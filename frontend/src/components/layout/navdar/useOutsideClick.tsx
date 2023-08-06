import { useEffect, useRef } from 'react';

export const useOutsideClick = (callback: () => void) => {
  const ref = useRef<HTMLDivElement>(null);

  useEffect(() => {
    const handleClickOutside = (event: MouseEvent) => {
      if (ref.current && !ref.current.contains(event.target as Node)) {
        callback();
      }
    };

    document.addEventListener('mousedown', handleClickOutside);

    return () => {
      document.removeEventListener('mousedown', handleClickOutside);
    };
  }, [callback]);

  return ref;
};

//   const menuRef = useRef<HTMLDivElement>(null);

//   useEffect(() => {
// 		const handleClickOutside = (event:MouseEvent) => {
// 		if (menuRef.current && !menuRef.current.contains(event.target as Node)) {
// 			setIsOpen(false);
// 		}
// 		};

// 		document.addEventListener('mousedown', handleClickOutside);

// 		return () => {
// 		document.removeEventListener('mousedown', handleClickOutside);
// 		};
//   	}, []);
