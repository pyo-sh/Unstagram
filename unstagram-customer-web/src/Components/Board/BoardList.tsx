import React, { useEffect, useState } from 'react';
import BoardListBox from 'Styles/Board/BoardListBox';
import BoardItem from './BoardItem';
import { getBoardList } from 'APIs/BoardApi';

const BoardList: React.FC = () => {
  const [boards, setBoards] = useState<any[]>([]);

  const getData = async () => {
    const responseData: any = await getBoardList();
    setBoards(responseData);
  }
  useEffect(() => {
    getData();
  }, []);

  return (
    <BoardListBox>
      {boards.map((current, index) => {
        return <BoardItem
          key={index}
          board={current}
          />
      })}
    </BoardListBox>
  );
}

export default BoardList;
