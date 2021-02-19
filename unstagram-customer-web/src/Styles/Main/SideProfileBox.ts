import styled from 'styled-components';

export const SideProfileWrapper = styled.div`
    width: 293px;
    height: 0px;

    margin-left: 28px;
`;

interface styleProp {
    windowWidth: number
};

const SideProfileBox = styled.div<styleProp>`
    width: 293px;
    height: 100vh;
    margin-left: 28px;
    
    flex-direction: column;

    position: fixed;
    top: 88px;
    left: ${(props) => (props.windowWidth - 935) / 2 + 642}px;

    .SideProfile-User{
        height: 56px;

        margin: 18px 0 ${25 + 10}px;

        .SideProfile-User-Image{
            width: 56px;
            height: 56px;

            border: 1px solid gray;
            border-radius: 50%;
        }
        .SideProfile-User-Detail{
            margin-left: 12px;

            flex-direction: column;
            justify-content: center;
            align-items: flex-start;

            .SideProfile-User-Identification{
                color: rgba(var(--i1d,38,38,38),1);
                font-size: 14px;
                font-weight: 600;
                overflow-x: hidden;
                text-overflow: ellipsis;
            }
            .SideProfile-User-Name{
                font-size: 14px;
                font-weight: 400;
                line-height: 18px;
                overflow: hidden;
                text-overflow: ellipsis;
                white-space: nowrap;
                color: rgba(var(--f52,142,142,142),1);
            }
        }
    }
    .SideProfile-Footer{
        padding: 0 0 38px;

        flex-direction: column;

        .SideProfile-Explain{
            margin: 0 0 16px;

            color: rgba(var(--edc,199,199,199),1);
            font-size: 11px;
            font-weight: 400;
        }
        .SideProfile-Instagram{
            color: rgba(var(--edc,199,199,199),1);
            font-size: 11px;
            font-weight: 400;
            line-height: 13px;
            text-decoration: none;
            :visited{
                color: rgba(var(--edc,199,199,199),1);
            }
            :hover{
                text-decoration: none;
            }
        }
    }
`;

export default SideProfileBox;