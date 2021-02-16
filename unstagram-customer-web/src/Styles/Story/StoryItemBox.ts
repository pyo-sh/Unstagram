import styled from 'styled-components';

const StoryItemBox = styled.div`
    width: 80px;
    height: 82px;
    padding: 0 4px;

    flex-direction: column;
    align-items: center;

    .StoryItem-Identification{
        max-width: 74px;
        height: 14px;

        font-size: 12px;
        line-height: 14px;
        color: rgba(var(--i1d,38,38,38),1);
        overflow: hidden;
        white-space: nowrap;
        text-align: center;
        text-overflow: ellipsis;
    }

    .StoryItem-Button{
        width: 66px;
        height: 68px;
        padding: 0;

        border: none;
        background: none;

        .StoryItem-Profile{
            width: 66px;
            height: 66px;

            /* position: absolute;
            top: -5px;
            left: -5px;  */
        }
    }
    .StoryItem-Button:focus {
        outline: none;
    }
`;

export default StoryItemBox;