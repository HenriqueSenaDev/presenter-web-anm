import "./styles.css";

const JoinCard = () => {
    return (
        <main className="join--event--main">
            <div className="join--card--container">
                <div className="form--container">
                    <h1>Digite o código do evento:</h1>
                    <input type="text"></input>
                </div>
                <div className="form--container">
                    <h1>Digite a senha do evento:</h1>
                    <input type="text"></input>
                </div>
                <button>
                    Entrar
                </button>
            </div>
        </main>
    );
}

export default JoinCard;