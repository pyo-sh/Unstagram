import React from 'react';
import BoardItemBox from 'Styles/Board/BoardItemBox';
import BoardHeader from './BoardHeader';
import BoardMenu from './BoardMenu';
import BoardComment from './BoardComment';
import BoardContent from './BoardContent';

interface PropType {
  board: {
    idx: number,
    content: string,
    pictures: any,
    reportedDate: string
  }
}

const BoardItem: React.FC<PropType> = ({ board }) => {
  console.log(board);
  return (
    <BoardItemBox>
      <BoardHeader/>
      <div className="BoardItem-Image-Wrapper">
        <img src={"http://localhost:8080/boardpicture/" + board.pictures[0].idx + "/"}></img>
      </div>
      <BoardMenu/>
      <BoardContent
        content={board.content}
        />
      <BoardComment/>
    </BoardItemBox>
  );
}

export default BoardItem;
