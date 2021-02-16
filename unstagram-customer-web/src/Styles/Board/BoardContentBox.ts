import styled from 'styled-components';

const BoardContentBox = styled.section`
    width: 100%;

    flex-direction: column;

    font-size: 14px;

    .BoardContent-Likes{
        padding: 0 16px;
        margin: 0 0 8px;

        white-space: pre;
        font-weight: 600;
        color: rgba(var(--i1d,38,38,38),1);
    }

    .BoardContent-Content{
        padding: 0 16px;
        display: inline;
        
        .BoardContent-Author{
            height: 19px;
            float: left;
            margin: 0;

            display: inline;
            position: relative;
            font-weight: 600;
        }
    }

    .BoardContent-Date{
        padding: 0 0 0 16px;
        margin: 0 0 4px;
        font-size: 10px;
        color: rgba(var(--f52,142,142,142),1);
    }
`;

export default BoardContentBox;