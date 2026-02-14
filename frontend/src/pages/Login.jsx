import React, { useState } from 'react';
import { useAuth } from '../context/AuthContext';
import { useNavigate, Link } from 'react-router-dom';

export function Login() {
    const [email, setEmail] = useState('');
    const { login } = useAuth();
    const navigate = useNavigate();

    const handleSubmit = (e) => {
        e.preventDefault();
        login(email);
        navigate('/profile');
    };

    return (
        <div className="container py-5 animate-fade-in d-flex align-items-center justify-content-center" style={{ minHeight: '80vh' }}>
            <div className="card shadow-sm border-0 p-4" style={{ maxWidth: '400px', width: '100%' }}>
                <h2 className="text-center fw-bold mb-4">Welcome Back</h2>
                <form onSubmit={handleSubmit}>
                    <div className="mb-3">
                        <label className="form-label fw-semibold">Email</label>
                        <input
                            type="email"
                            className="form-control"
                            placeholder="you@example.com"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            required
                        />
                    </div>
                    <button type="submit" className="btn btn-primary w-100 py-2 mb-3">Log In</button>
                </form>
                <p className="text-center text-muted small mb-0">
                    Don't have an account? <Link to="/register" className="text-primary text-decoration-none fw-semibold">Register</Link>
                </p>
            </div>
        </div>
    );
}
