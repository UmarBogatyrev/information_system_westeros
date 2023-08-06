

export interface TableXXL {
  children: React.ReactNode;
}

export default function TableContext({ 
  children
}: TableXXL) {
  return <div className='mb-[31px] w-[1032px] shadow-md rounded-[45px]'>{children}</div>;
}

// Пример обертки