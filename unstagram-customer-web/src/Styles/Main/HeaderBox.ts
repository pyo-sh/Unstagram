import styled from 'styled-components';

const HeaderBox = styled.div`
    width: 100vw;
    height: 54px;
    justify-content: center;
    align-items: center;

    position: fixed;
    top: 0;
    z-index: 3;

    border-bottom: 1px solid rgba(var(--b6a,219,219,219),1);
    background-color: rgba(var(--d87,255,255,255),1);

    .Header-Wrapper{
        max-width: 975px;
        width: 100%;
        height: 54px;

        padding: 0 20px;
        
        justify-content: space-between;
        align-items: center;
    }

    .Header-Logo{
        min-width: 103px;
        max-width: 360px;
        width: 100%;
        height: 29px;

        font-size: 24px;
        font-weight: bold;
    }
    .Header-Search{
        justify-content: center;
        align-items: center;
        .Header-Search-Icon{
            width: 10px;
            height: 10px;

            position: relative;
            left: ${10 + 11}px;
            
        }
        .Header-Search-Input{
            width: 215px;
            height: 28px;
            font-size: 14px;
            font-weight: 300;
            padding: 3px 10px 3px 26px;

            justify-content: center;

            background: rgba(var(--b3f,250,250,250),1);
            border: solid 1px rgba(var(--b6a,219,219,219),1);
            border-radius: 3px;
        }
        .Header-Search-Input:focus{
            border: solid 1px rgba(var(--b6a,219,219,219),1);
            outline: none;
        }
    }

    .Header-Menu-Wrapper{
        min-width: 103px;
        max-width: 360px;
        width: 100%;
        height: 22px;

        justify-content: flex-end;
    }
`;

export default HeaderBox;