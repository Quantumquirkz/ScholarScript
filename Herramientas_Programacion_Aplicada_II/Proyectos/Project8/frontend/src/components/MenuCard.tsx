import { MenuItem } from '../types';
import { Card, CardContent } from './ui/card';
import { Button } from './ui/button';
import { Badge } from './ui/badge';
import { ShoppingCart, Star } from 'lucide-react';

interface MenuCardProps {
    item: MenuItem;
    onAdd: (item: MenuItem) => void;
}

export function MenuCard({ item, onAdd }: MenuCardProps) {
    return (
        <Card className="hover:shadow-lg transition-all duration-300 hover:scale-105 cursor-pointer border-2 hover:border-yellow-400">
            <CardContent className="p-4">
                <div className="flex justify-between items-start mb-2">
                    <h4 className="text-lg font-semibold flex items-center gap-2">
                        {item.name}
                        {item.isCombo && (
                            <Badge className="bg-red-500 text-white animate-pulse">
                                <Star className="h-3 w-3 mr-1" />
                                COMBO
                            </Badge>
                        )}
                    </h4>
                    <span className="text-2xl font-black text-green-600">
                        ${item.price.toFixed(2)}
                    </span>
                </div>
                <p className="text-gray-600 mb-3">{item.description}</p>
                <Button
                    onClick={() => onAdd(item)}
                    className="w-full bg-gradient-to-r from-red-500 to-yellow-500 hover:from-red-600 hover:to-yellow-600 text-white font-bold"
                >
                    <ShoppingCart className="h-4 w-4 mr-2" />
                    Agregar al pedido
                </Button>
            </CardContent>
        </Card>
    );
} 